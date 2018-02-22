
(ns script.core
  (:require [goog.dom :as dom]
            [socket.io :as io]))



(enable-console-print!)

(println "Hello clojurescript!")

(js-invoke js/socket "emit" "hello" "clojure")
