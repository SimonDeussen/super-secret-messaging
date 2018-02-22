
(ns script.core
  (:require [goog.dom :as dom]
            [socket.io :as io]))



(enable-console-print!)

(println "Hello clojurescript!")

(js-invoke js/socket "emit" "hello" "clojure")

(comment "
TODO

SAVE

getMessage()
  ret message

encryptMessage(message)
  ret key
      cmessage

hashMessage(cmessage)
  ret dbKey

saveMessage(dbkey, cmessage)

GET

splitUrl()
  ret dkbey
      key

getDataAndDelete(dkbey)
  ret cmessage

decryptMessage(key, cmessage)
  ret message

")
