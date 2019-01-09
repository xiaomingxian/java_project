function add(x, y) {
    return x + y
}

function jian(x, y) {
    return x - y
}

model.exports.add = add;
model.exports.jian = jian;
//导出多个方法2
// model.exports = {add, jian};