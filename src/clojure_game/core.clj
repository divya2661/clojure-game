(ns clojure-game.core
  (:import java.net.ServerSocket)
  (:gen-class))


(defn read-message
  "read messages from a given client socket"
  [client-socket]
  (let [reader (clojure.java.io/reader client-socket)]
    (.readLine reader)))

(defn write-message [client-socket message]
  "write messages into a given client socket"
  (let [writer (clojure.java.io/writer client-socket)]
    (.write writer message)
    (.flush writer)))

(defn -main [& args]
  (let [port 1234]
    (with-open [server-socket (ServerSocket. port)
                client-socket (do (prn "started the server")
                                  (.accept server-socket))]
      (let [message (read-message client-socket)]
        (prn message)
        (write-message client-socket (str " from server " message))))))
