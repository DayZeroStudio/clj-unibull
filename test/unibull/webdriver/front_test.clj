(ns unibull.webdriver.front-test
  (:require
    [midje.sweet :refer :all]
    [clj-webdriver.taxi :refer :all]
    [unibull.app :refer [app]]
    [unibull.config :refer [env]]
    [ring.adapter.jetty :refer [run-jetty]]
    ))

(def cfg (:web (:test env)))
(def server (atom nil))

(defn start-server! []
  (println :server/start)
  (reset! server (run-jetty app (merge {:join? false}
                                       cfg))))

(defn stop-server! []
  (println :server/stop)
  (swap! server #(.stop %)))

(defn to-home []
  (to (str "http://localhost:" (:port cfg))))

;; SETUP
(start-server!)

(set-driver! {:browser :firefox})

(background
  (around :facts (do (to-home) ?form)))

(fact "works"
      (title) => "UniBull")

;; CLEANUP
(quit)
(stop-server!)
