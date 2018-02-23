(ns ssm.core
  (:require   [goog.dom :as dom]
              [clojure.string :as str]
               ))

(enable-console-print!)

(defn seperator [] ".")


(println "Hello clojurescript!")
(js-invoke js/socket "emit" "hello" "clojure")

(defn emit-socket [socket-name msg]
  (js-invoke js/socket "emit" socket-name msg))

(defn on-socket [socket-name function]
  (js-invoke js/socket "on" socket-name function))

(on-socket "test" (fn [msg] (println msg)))

(defn get-text-content [id]
  (-> js/document
    (.getElementById id)
    (.-value)))

(defn get-inner-html [id]
  (-> js/document
    (.getElementById id)
    (.-innerHTML)))

(defn remove-element [id]
  (-> js/document
    (.getElementById id)
    (.remove)))

(defn get-element [id]
  (-> js/document
    (.getElementById id)))

(defn delete-hidden-class [id]
  (-> js/window
    (.eval (str "document.getElementById('" id "').classList.remove('hidden')"))))

(defn hide-element [id]
  (-> js/window
    (.eval (str "document.getElementById('" id "').classList.add('hidden')"))))


(defn get-location []
  (aget js/location "href"))


(defn get-url-hash []
    (str (last (str/split (get-location) #"/"))))

(defn has-valid-length []
  (println "len")
    (if
      (= (count (get-url-hash)) 65) (println "right length")
      ))

(println (get-location))

(defn show-success []
  (delete-hidden-class "message-found-text")
  (delete-hidden-class "show-button")
  )

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

(defn encrypt-my-message []
  (def my-msg (get-text-content "textInput"))
  (def my-hash (build-hash my-msg))
  (def my-db-key (subs my-hash 0 12))
  (def my-key (subs my-hash 12 64))
  (def my-secret (encrypt my-msg my-key))
  {:key my-key :db-key my-db-key :secret my-secret}
  )


(defn add-click [id handler]
  (.addEventListener (get-element id) "click" handler))

(defn display-url [url]
  (def url-string (str (get-location) (url :db-key) (seperator) (url :key)))
  (println url-string)
  (remove-element "textInput")
  (remove-element "submit")
  (delete-hidden-class "go-to-home")
  (delete-hidden-class "link-wrapper")
  (aset (get-element "result-url") "textContent" url-string)
  (-> js/document
    (.getElementById "result-url")
    (.setAttribute "href" url-string)))


(defn button-click []
  (def encrypted-msg (encrypt-my-message))
  (display-url encrypted-msg)
  (println (str (str (encrypted-msg :db-key)) (seperator) (str (encrypted-msg :secret)) ))

  (emit-socket
    "writeIntoDb"
    (str (str (encrypted-msg :db-key)) (seperator) (str (encrypted-msg :secret)) )))

(defn message-exists []
  (show-success)
  (.addEventListener (get-element "show-button") "click"
    (fn []
      (emit-socket "requestData" (first (str/split (get-url-hash) (seperator))))
      ))
  )

(defn message-doesnt-exists []
  (println "oh nooooooo")
  (delete-hidden-class "message-not-found-headline"))

(defn message-handler [msg]
  (cond
    (= msg "true") (message-exists)
    (= msg "false") (message-doesnt-exists)))

(defn todo-save []
  (println "save")
  (add-click "submit" button-click))

(defn todo-get []
  (println "get")
  (emit-socket "containsMessage" (first (str/split (get-url-hash) (seperator)))))

(on-socket "isInDatabase"
  (fn [msg]
    (message-handler msg)))

(on-socket "getData"
  (fn [msg]
    (println msg)
    (hide-element "show-button")
    (hide-element "message-found-text")
    (delete-hidden-class "go-to-home")
    (aset (get-element "message") "textContent" (decrypt msg (last (str/split (get-url-hash) (seperator)))))))

(defn do-my-stuff []
    (cond
      (= (str (get-inner-html "state")) "save-message") (todo-save)
      (= (str (get-inner-html "state")) "get-message") (todo-get)
      :else (println "no")))

(do-my-stuff)
