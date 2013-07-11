var map = function() {
	if (this.sresult === "AC") {
		emit(this.uid, {ac:1, tot:1});
	} else {
		emit(this.uid, {ac:0, tot:1});
	}
};