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
script.core.getTextContent = (function script$core$getTextContent(id){
return document.getElementById(id).value;
});
script.core.getElement = (function script$core$getElement(id){
return document.getElementById(id);
});
script.core.addClick = (function script$core$addClick(id,handler){
return script.core.getElement.call(null,id).addEventListener("click",handler);
});
script.core.dummyClick = (function script$core$dummyClick(){
script.core.emitSocket.call(null,"hello",script.core.getTextContent.call(null,"textInput"));

return cljs.core.println.call(null,script.core.getTextContent.call(null,"textInput"));
});
script.core.addClick.call(null,"submit",script.core.dummyClick);

//# sourceMappingURL=core.js.map
