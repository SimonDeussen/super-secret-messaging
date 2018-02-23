(ns ssm.core
  (:require   [goog.dom :as dom]
               ))

(enable-console-print!)

(println "Hello clojurescript!")
(js-invoke js/socket "emit" "hello" "clojure")

(defn emit-socket [socketName msg]
  (js-invoke js/socket "emit" socketName msg))


(defn hash-md5 [input]
  (-> js/Crypt
    (.-HASH)
    (.sha256 input)
    (.toString)))

(defn build-hash [input]
  (hash-md5
    (str
      input
      (js-invoke js/Date "now")
      (aget js/navigator "userAgent"))))

(defn encrypt [input key]
  (-> js/Crypt
    (.-AES)
    (.encrypt input key)))

(defn decrypt [input key]
  (-> js/Crypt
    (.-AES)
    (.decrypt input key)))

; (def my-msg "blablabssslab")
; (def my-hash (hash-md5 my-msg))
; (def my-db-key (subs my-hash 0 12))
; (def my-key (subs my-hash 12 64))
; (def my-secret (encrypt my-msg my-key))
; (def my-plain (decrypt my-secret my-key))
;
; (println (str "plain text: " my-msg))
; (println (str "my-hash:    " my-hash))
; (println (str "my-key:     " my-key))
; (println (str "my-db-key:  " my-db-key))
; (println (str "encrypted:  " my-secret))
; (println (str "decrypted:  " my-plain))



(defn get-text-content [id]
  (-> js/document
    (.getElementById id)
    (.-value)))

(defn get-element [id]
  (-> js/document
    (.getElementById id)))

(defn encrypt-my-message []
  (def my-msg (get-text-content "textInput"))
  (def my-hash (build-hash my-msg))
  (def my-db-key (subs my-hash 0 12))
  (def my-key (subs my-hash 12 64))
  (def my-secret (encrypt my-msg my-key))
  (str my-db-key "%" my-secret)
  )


(defn add-click [id handler]
  (.addEventListener (get-element id) "click" handler))

(defn dummy-click []
  (emit-socket
    "writeIntoDb"
    (encrypt-my-message)))

(add-click "submit" dummy-click)

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
