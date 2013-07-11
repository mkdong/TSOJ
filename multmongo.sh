#/bin/sh
sudo /usr/bin/mongod -dbpath /var/lib/mongodbs/masterdb --port 10000 --master &
sudo /usr/bin/mongod -dbpath /var/lib/mongodbs/slavedb1 --source localhost:10000 --slave --port 10001 &
sudo /usr/bin/mongod -dbpath /var/lib/mongodbs/slavedb2 --source localhost:10000 --slave --port 10002 &
