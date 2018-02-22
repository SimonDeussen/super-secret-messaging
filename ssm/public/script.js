let socket = io();

console.log("hello world")

socket.emit("hello", "javascript")

var Crypt = new Crypt();  // constructor

/*** encrypt */
var ciphertext = Crypt.AES.encrypt("plaintext");
console.log(ciphertext);
