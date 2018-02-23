  (ns ssm.core
    (:require   [goog.dom :as dom]

                 ))

  (enable-console-print!)

  (println "Hello clojurescript!")
  (js-invoke js/socket "emit" "hello" "clojure")

  (defn emitSocket [socketName msg]
    (js-invoke js/socket "emit" socketName msg))

(defn hash-md5 [input]
  (-> js/Crypt
    (.-HASH)
    (.sha256 input)
    (.toString)))

(defn encrypt [input key]
  (-> js/Crypt
    (.-AES)
    (.encrypt input key)))

(defn decrypt [input key]
  (-> js/Crypt
    (.-AES)
    (.decrypt input key)))

(def my-msg "blablablab")
(def my-key (hash-md5 my-msg))
(def my-secret (encrypt my-msg my-key))
(def my-plain (decrypt my-secret my-key))

(println my-msg)
(println my-key)
(println my-secret)
(println my-plain)

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
