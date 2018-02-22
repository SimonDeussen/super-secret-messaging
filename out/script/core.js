// Compiled by ClojureScript 1.9.946 {}
goog.provide('script.core');
goog.require('cljs.core');
goog.require('goog.dom');
goog.require('socket.io');
cljs.core.enable_console_print_BANG_.call(null);
cljs.core.println.call(null,"Hello clojurescript!");
cljs.core.js_invoke.call(null,socket,"emit","hello","clojure");
script.core.emitSocket = (function script$core$emitSocket(socketName,msg){
return cljs.core.js_invoke.call(null,socket,"emit",socketName,msg);
});
script.core.emitSocket.call(null,"hello","asdfadfafa");

//# sourceMappingURL=core.js.map
