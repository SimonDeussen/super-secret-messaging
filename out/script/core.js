// Compiled by ClojureScript 1.9.946 {}
goog.provide('script.core');
goog.require('cljs.core');
goog.require('goog.dom');
goog.require('socket.io');
cljs.core.enable_console_print_BANG_.call(null);
cljs.core.println.call(null,"Hello clojurescript!");
cljs.core.js_invoke.call(null,socket,"emit","hello","clojure");

//# sourceMappingURL=core.js.map
