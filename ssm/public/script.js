let socket = io();

console.log("hello world")

socket.emit("hello", "javascript")

var Crypt = new Crypt();  // constructor
var digest_sha1 = Crypt.HASH.sha1("message");
console.log(digest_sha1.toString());
