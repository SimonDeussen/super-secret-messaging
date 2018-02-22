
(ns script.core
  (:require [goog.dom :as dom]
            [socket.io :as io]))



(enable-console-print!)

(println "Hello clojurescript!")
(js-invoke js/socket "emit" "hello" "clojure")

(defn emitSocket [socketName msg]
  (js-invoke js/socket "emit" socketName msg))


(defn getTextContent [id]
  (-> js/document
    (.getElementById id)
    (.-value)))

(defn getElement [id]
  (-> js/document
    (.getElementById id)))


(defn addClick [id handler]
  (.addEventListener (getElement id) "click" handler))

(defn dummyClick []
  (emitSocket
    "hello"
    (getTextContent "textInput"))
  (println (getTextContent "textInput")))

(addClick "submit" dummyClick)

(comment "
TODO



SAVE

getMessage() DONE
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
