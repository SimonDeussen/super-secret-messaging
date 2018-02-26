var  socket = io();
var Crypt = new Crypt();

let mySha256 = Crypt.HASH.sha256


let hashTest = mySha256("123456789").toString();
console.log(hashTest);
