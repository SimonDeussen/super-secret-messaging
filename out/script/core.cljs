
(ns script.core
  (:require [goog.dom :as dom]
            [socket.io :as io]))



(enable-console-print!)

(println "Hello clojurescript!")
(js-invoke js/socket "emit" "hello" "clojure")

(defn emitSocket [socketName msg]
  (js-invoke js/socket "emit" socketName msg))

(emitSocket "hello" "asdfadfafa")

; (emitSocket "hello" "from clojurescript")

; (defn getTextContent [id]
;   (-> js/document
;     (.getElementById id)
;     (.-value)))
;
; (defn getElement [id]
;   (-> js/document
;     (.getElementById id)))
;
; (println (getTextContent "submit"))
;
;
; (defn addClick [id handler]
;   (.addEventListener (getElement id) "click" handler))
;
; (addClick "submit"
;   (fn a []
;   (emitSocket
;     "hello"
;     (getTextContent "textInput"))
;   (println (getTextContent "textInput"))))

; (comment "
; TODO
;
;
;
; SAVE
;
; getMessage() DONE
;   ret message
;
; encryptMessage(message)
;   ret key
;       cmessage
;
; hashMessage(cmessage)
;   ret dbKey
;
; saveMessage(dbkey, cmessage)
;
; GET
;
; splitUrl()
;   ret dkbey
;       key
;
; getDataAndDelete(dkbey)
;   ret cmessage
;
; decryptMessage(key, cmessage)
;   ret message
; ")
