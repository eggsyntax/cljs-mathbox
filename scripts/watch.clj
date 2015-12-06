(require '[cljs.build.api :as b])

(b/watch "src"
  {:main 'cljs-mathbox.core
   :output-to "out/cljs_mathbox.js"
   :output-dir "out"})
