goog.addDependency("base.js", ['goog'], []);
goog.addDependency("../node_modules/socket.io-client/dist/socket.io.js", ['socket.io'], []);
goog.addDependency("../cljs/core.js", ['cljs.core'], ['goog.string', 'goog.Uri', 'goog.object', 'goog.math.Integer', 'goog.string.StringBuffer', 'goog.array', 'goog.math.Long']);
goog.addDependency("../process/env.js", ['process.env'], ['cljs.core']);
goog.addDependency("../script/core.js", ['script.core'], ['goog.dom', 'socket.io', 'cljs.core']);
