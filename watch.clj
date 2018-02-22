(require 'cljs.build.api)

(cljs.build.api/watch "src"
  {:main 'script.core
   :foreign-libs [{:file "node_modules/socket.io-client/dist"
                   :provides "socket.io"}]
   :output-to "out/main.js"})