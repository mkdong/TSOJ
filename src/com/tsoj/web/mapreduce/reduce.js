var reduce = function (key, values) {
    var ac = 0;
    var tot = 0;
    for (var i=0; i<values.length; ++i) {
        ac += values[i][0];
        tot += values[i][1];
    }
    return {ac: ac, tot: tot};
};