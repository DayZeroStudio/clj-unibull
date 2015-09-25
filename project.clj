(defproject unibull "0.1.0-SNAPSHOT"
  :description "FIXME: write this!"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies [; SERVER
                 [org.clojure/clojure "1.7.0"]
                 [org.danielsz/system "0.1.9"]
                 [ring "1.4.0"]
                 [fogus/ring-edn "0.3.0"]
                 [ring/ring-defaults "0.1.5"]
                 [compojure "1.4.0"]
                 [com.datomic/datomic-free "0.9.4815.12"]

                 ; BOTH
                 [midje "1.7.0"]
                 [org.clojure/core.async "0.1.346.0-17112a-alpha"]

                 ;; CLIENT
                 [org.clojure/clojurescript "1.7.122"]
                 [quiescent "0.2.0-alpha1"]
                 [cljs-ajax "0.3.14"]
                 ]
  :profiles {:dev
             {:dependencies [[clj-webdriver "0.7.2"]
                             [org.seleniumhq.selenium/selenium-server "2.47.0"]]
              :datomic {:config "resources/datomic/free-transactor-template.properties"
                        :db-uri "datomic:free://localhost:4334/unibull"}}}
  :datomic {:schemas ["resources/datomic" ["schema.edn"]]}

  :plugins [[lein-cljsbuild "1.1.0"]
            [lein-figwheel "0.4.0"]
            [lein-datomic "0.2.0"]
            ]

  :source-paths ["src/clj"]
  :test-paths ["test"]

  :clean-targets ^{:protect false} ["resources/public/js/compiled" "target"]

  :cljsbuild {:builds
              [{:id "dev"
                :source-paths ["src/cljs"]
                :figwheel {:on-jsload "unibull.core/on-js-reload" }
                :compiler {:main unibull.core
                           :asset-path "js/compiled/out"
                           :output-to "resources/public/js/compiled/unibull.js"
                           :output-dir "resources/public/js/compiled/out"
                           :source-map-timestamp true }}

               {:id "min"
                :source-paths ["src/cljs"]
                :compiler {:output-to "resources/public/js/compiled/unibull.js"
                           :main unibull.core
                           :optimizations :advanced
                           :pretty-print false}}]}

  :figwheel {
             :http-server-root "public" ;; default and assumes "resources"
             :server-port 3449 ;; default
             :server-ip "127.0.0.1"

             :css-dirs ["resources/public/css"] ;; watch and update CSS

             ;; Start an nREPL server into the running figwheel process
             ;; :nrepl-port 7888
             ;; :nrepl-middleware []
             ;; if you want to disable the REPL
             ;; :repl false

             ;; $2 ln, $1 filepath
             ;; :open-file-command "myfile-opener"

             ;; to configure a different figwheel logfile path
             ;; :server-logfile "tmp/logs/figwheel-logfile.log"
             })
