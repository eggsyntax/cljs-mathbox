;; Define cljs wrappers for MathBox js functions
;; See https://github.com/unconed/MathBox.js/tree/legacy for MathBox documentation
;; Find usage examples at (https://github.com/eggsyntax/cljs-mathbox-example)

;; Note that requiring cljsjs.mathbox puts a MathBox instance into the top-level
;; namespace, accessible as js/mathBox, as well as making ThreeBox available as
;; js/ThreeBox
;;TODO Consider handling the hash-sign id on this side


(ns cljs-mathbox.mathbox
  (:require [cljs.core.async :as async]
            [cljsjs.mathbox])
  (:require-macros [cljs.core.async.macros :refer [go]]))

(defn create-mathbox
  "Create and return a mathbox instance. mathBox must be defined as a top-level
  JS variable (which just means loading the library in your HTML file). The instance will be attached to a div you name (or 'mathboxdiv' if divname is nil)"
  [shader-file opts-map & divname]
  (let [defaults {:cameraControls true
                  :cursor true
                  :stats false
                  :elementResize true
                  ;; Just in case (eg if there's no data)
                  :expression (constantly 0)}
        mb-div (or (first divname) "mathboxdiv")

        containing-element (.getElementById js/document mb-div)

        ;; Create mathbox attached to a containing element, if one is provided,
        ;; otherwise attach it to the page (by omitting the containing-element
        ;; argument, see
        ;; https://github.com/unconed/MathBox.js/blob/legacy/README.md
        mb (if containing-element
             (.start (js/mathBox. containing-element
                                  (clj->js (into defaults opts-map))))
             (.start (js/mathBox. (clj->js (into defaults opts-map)))))

        ;; Preload shader file
        shader-filename (clj->js [shader-file])
        ;; .preload wants a callback; for our purposes a noop will do fine
        _ (.preload js/ThreeBox shader-filename #())]
    mb))

;; Primitives:

(defn platonic [mb-instance opts-map]
  (. mb-instance platonic (clj->js opts-map)))

(defn curve [mb-instance opts-map]
  (. mb-instance curve (clj->js opts-map)))

(defn bezier [mb-instance opts-map]
  (. mb-instance bezier (clj->js opts-map)))

(defn bezier-surface [mb-instance opts-map]
  (. mb-instance bezierSurface (clj->js opts-map)))

(defn axis [mb-instance opts-map]
  (. mb-instance axis (clj->js opts-map)))

(defn grid [mb-instance opts-map]
  (. mb-instance grid (clj->js opts-map)))

(defn surface [mb-instance opts-map]
  (. mb-instance surface (clj->js opts-map)))

;; (defn point
;;   "MathBox has no native"
;;   [mb-instance opts-map])

(defn mb-vector
  "Named mb-vector in order to not shadow core/vector"
  [mb-instance opts-map]
  (. mb-instance vector (clj->js opts-map)))

;; Kill a primitive
(defn mb-remove
  "Remove an object from the stage, with animation props to control eg duration"
  [mb-instance id animation-opts-map]
  (. mb-instance remove id (clj->js animation-opts-map)))

;; Getting & setting properties:

;;(print "#acube props: " (mb/get-props js/mathbox "#acube"))
(defn get-props [mb-instance id]
  (js->clj (.get mb-instance id)))

(defn get-prop [mb-instance id prop-name]
  (let [key (clj->js prop-name)]
    (get (get-props mb-instance id) key)))

;;(mb/set-prop js/mathbox "#acube" {:opacity 0.7})
(defn set-props! [mb-instance id opts-map]
  (.set mb-instance id (clj->js opts-map)))

;; Viewport & camera:

(defn viewport [mb-instance opts-map]
  (. mb-instance viewport (clj->js opts-map)))

(defn camera [mb-instance opts-map]
  (. mb-instance camera (clj->js opts-map)))

;; Animate & clone. Note that both of these always happen in a separate go block so
;; as to be non-blocking.

(defn animate [mb-instance id new-opts-map animation-opts-map]
  (go
    (. mb-instance animate
       id
       (clj->js new-opts-map)
       (clj->js animation-opts-map)))
  mb-instance)

(defn mb-clone
  "Named mb-clone in order to not shadow core/clone"
  [mb-instance id new-opts-map animation-opts-map]
  (go
    (. mb-instance clone
       id
       (clj->js new-opts-map)
       (clj->js animation-opts-map)))
  mb-instance)
