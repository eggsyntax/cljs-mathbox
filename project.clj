(defproject cljs-mathbox "1.0.0"
  :description "ClojureScript wrapper for the MathBox JS library"
  :url "https://github.com/eggsyntax/cljs-mathbox"
  :license "Eclipse Public License"
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [cljsjs/mathbox "0.0.1-0"]
                 [org.clojure/core.async "0.2.374"]
                 [org.clojure/clojurescript "1.7.170" :classifier "aot"
                  :exclusion [org.clojure/data.json]]
                 [org.clojure/data.json "0.2.6" :classifier "aot"]]
  :jvm-opts ^:replace ["-Xmx1g" "-server"]
  :plugins [[lein-npm "0.6.1"]]
  :npm {:dependencies [[source-map-support "0.3.2"]]}
  :source-paths ["src" "target/classes"]
  :clean-targets ["out" "release"]
  :target-path "target")
