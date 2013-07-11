package com.tsoj.web.evaluator;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import javax.annotation.Resource;

import com.tsoj.web.entity.Solution;
import com.tsoj.web.service.SolutionService;

public class Evaluator {
	private class Task{
		private int pid;
		private String fileName;
		private Solution solution;
		
		private class InnerExecuter extends Thread{
			private String binary;
			private String iFileName;
			private String ansFileName;
			private long time;
			private long memory;
			private String result;
			
			public InnerExecuter(String binary, String iFileName, String ansFileName) {
				this.binary = binary;
				this.iFileName = iFileName;
				this.ansFileName = ansFileName;
				this.result = "JUDGING";
				this.time = 0;
				System.out.println("IE: binary: " + binary);
				System.out.println("IE: iFileName: " + iFileName);
				System.out.println("IE: ansFileName: " + ansFileName);
			}
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					System.out.println("InnerExecture: Run");
					long m = Runtime.getRuntime().freeMemory();
					long t = System.currentTimeMillis();
					System.out.println("InnerExecture: Run");
					Process pro = Runtime.getRuntime().exec(binary);// + " < " + iFileName + " > " + ansFileName);
					//
					char buff[] = new char[1024];
					//output to binary
					OutputStreamWriter osr = new OutputStreamWriter(pro.getOutputStream()); 
					InputStreamReader isr = new InputStreamReader(new FileInputStream(iFileName));
					int count = 0;
					while ((count = isr.read(buff)) != -1) {
						osr.write(buff, 0, count);
					}
					isr.close();
					osr.close();
					//input from binary
					isr = new InputStreamReader(pro.getInputStream());
					osr = new OutputStreamWriter(new FileOutputStream(ansFileName));
					count = 0;
					while ((count = isr.read(buff)) != -1) {
						osr.write(buff, 0, count);
					}
					isr.close();
					osr.close();
					
					System.out.println("InnerExecture: Run");
					if (0 != pro.waitFor()) {
						this.result = "RE";
						return;
					}
					time= System.currentTimeMillis() - t;
//					memory = m - Runtime.getRuntime().freeMemory();
					memory = Runtime.getRuntime().totalMemory()/1000;
					System.out.println("InnerExecture: " + String.valueOf(time));
					System.out.println("InnerExecture: " + String.valueOf(memory));
					this.result = "OK";
				} catch (IOException | InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("InnerExecture: ERROR");
					
				}
			}
			public long getTime() {
				return this.time;
			}
			public String getResult() {
				return this.result;
			}
			public long getMemory() {
				return this.memory;
			}
		}
		
		public Task(Solution solution) {
			this.solution = solution;
			this.pid = solution.getPid();
			this.fileName = solution.getScode();
		}
		
		public Solution judge() {
			String name = fileName.substring(fileName.lastIndexOf('/')+1, fileName.lastIndexOf('.'));
			String root = fileName.substring(0, fileName.lastIndexOf('/'));
			root = root.substring(0, root.lastIndexOf('/'));
			root += "/";
			String testRoot = root + "testing/";
			String dataRoot = root + "data/";
			String tmpFileName = testRoot + name + ".cpp";
			String binary = testRoot + name;
			String iFileName = dataRoot + String.valueOf(pid) + ".in";
			String oFileName = dataRoot + String.valueOf(pid) + ".out";
			String ansFileName = testRoot + name + ".ans";
			
			try {
				Process pro = Runtime.getRuntime().exec("/bin/cp " + fileName + " " + tmpFileName);
				if (0 != pro.waitFor()) {
					solution.setSresult("EE");
					return solution;
				}
				pro = Runtime.getRuntime().exec("g++ -o " + binary + " " + tmpFileName);
				if (0 != pro.waitFor()) {
					solution.setSresult("CE");
					return solution;
				}
				InnerExecuter t = new InnerExecuter(binary, iFileName, ansFileName);
				t.start();
				t.join(5000);
				solution.setStime((int)t.getTime());
				solution.setSmemory(502);
				solution.setSresult(t.getResult());
				if (t.getState() != Thread.State.TERMINATED) {
					solution.setSresult("TLE");
					return solution;
				}
				
				if (solution.getSresult().equals("RE")) {
					return solution;
				}
				
				pro = Runtime.getRuntime().exec("/usr/bin/diff " + ansFileName + " " + oFileName);
				if (0 != pro.waitFor()) {
					solution.setSresult("WA");
					return solution;
				}
				solution.setSresult("AC");
				return solution;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		
	}
	private Queue<Task> tasks = new LinkedList<Task>();
	private Judger judger;
	@Resource
	private SolutionService solutionService;
	
	public synchronized void append(Solution solution) {
		System.out.println("Evaluator: appending");
		synchronized(tasks) {
			//lock.lock();
			tasks.add(new Task(solution));
			tasks.notifyAll();
			//lock.unlock();
		}
		System.out.println("Evaluator: appended");
	}
	
	private class Judger extends Thread{
		public void run() {
			while (true) {
				System.out.println("Judger: tasks size " + String.valueOf(tasks.size()));
				synchronized(tasks) {
					//lock.lock();
					if (tasks.size() > 0) {
						System.out.println("Judger: judging one");
						Task task = tasks.poll();
						Solution solution = task.judge();
						System.out.println("Judger: judging result: " + solution.getSresult());
						solution.setStesttime(new java.util.Date());
						solutionService.update(solution);
					}
					//lock.unlock();
					//lock.lock();
					try {
						if (tasks.size() == 0){
							System.out.println("Judger: waiting one");
							tasks.wait();
							System.out.println("Judger: here's one");
						}
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} finally {
						//lock.unlock();
					}
				}
			}
		}
		public Judger(Evaluator e) {
		}
	}
	
	public Evaluator() {
		System.out.println("Evaluator: starting");
		judger = new Judger(this);
		judger.start();
		System.out.println("Evaluator: started");
	}

}
