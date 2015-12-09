# cljs-mathbox

[cljs-mathbox](https://github.com/eggsyntax/cljs-mathbox) is a ClojureScript wrapper for the [MathBox 1](https://github.com/unconed/MathBox.js/tree/legacy) JavaScript mathematical visualization library.

## Overview

NOTE: This library is a thin wrapper. 99% of the documentation is on the [MathBox](https://github.com/unconed/MathBox.js/tree/legacy) page.

[MathBox](https://github.com/unconed/MathBox.js/tree/legacy) is an extremely powerful library for mathematical (and data) visualization in the browser, specializing in interactive, three-dimensional, animated visualizations, created by [Steven Wittens](http://acko.net/about/). It's built on the [Three.js](https://github.com/mrdoob/three.js/) library, which is built on [WebGL](https://en.wikipedia.org/wiki/WebGL). You can see a number of impressive MathBox visualizations at the author's website, [acko.net](http://acko.net/).

It should be noted that this library is based on MathBox version 1. MathBox version 2, which will be a very different, more powerful library, and is [nearing completion](http://acko.net/blog/mathbox2/) as of this writing (12/2015). Nevertheless, MathBox 1 is quite powerful in its own right.

For MathBox 1 documentation, see [its repo](https://github.com/unconed/MathBox.js/tree/legacy) (be aware that Mathbox 2 is [hosted elsewhere](https://gitgud.io/unconed/mathbox)). cljs-mathbox is a fairly thin wrapper over the original. It exposes the primitives (platonic, curve, bezier, surface, bezier-surface, vector, grid, axis, viewport, camera), and the major functions (animate, clone, remove, get-props, set-props).

You can see example usage of cljs-mathbox at the accompanying [cljs-mathbox-example](https://github.com/eggsyntax/cljs-mathbox-example). In short: include and require the library, create a mathbox instance, and call functions on it.

NOTE that you'll need at least one WebGL shader, and this repo [includes one](https://github.com/eggsyntax/cljs-mathbox/blob/master/shaders/snippets.glsl.html).

NOTE: when you load a cljs-mathbox visualization, you'll see a string of errors like "WebGL: INVALID_OPERATION: getUniformLocation: program not linked". This is a MathBox issue (related to the Three.js dependency), so cljs-mathbox is stuck with it.

## Library usage

Add the following dependency: `[cljs-mathbox "0.1.2-SNAPSHOT"]`
and then you can `(:require [cljs-mathbox.mathbox])`.

Simple example:

```clojurescript
(ns cljs-mathbox-example.core
  (:require [cljs-mathbox.mathbox :as mb]
            [cljs.core.async :refer [<! timeout]])
  (:require-macros [cljs.core.async.macros :refer [go]]))
(let [m (mb/create-mathbox "shaders/snippets.glsl.html" {} "mathboxdiv")]
  (go (<! (timeout 200))
      (-> m
          (mb/camera {:orbit 7.5})
          (mb/platonic {:type :cube
                        :id "acube"})
          (mb/animate "#acube"
                      {:mathRotation [0 3 0]}
                      {:duration 6000}))))
```
## Building this project:

NOTE: typically there's no need to build this project yourself unless you're intending to contribute to cljs-mathbox.

Build your project once in dev mode with the following script and then open `index.html` in your browser.

    ./scripts/build

To auto build your project in dev mode:

    ./script/watch

Build a single release artifact with the following script and then open `index_release.html` in your browser.

    ./scripts/release

The project is based on the [mies template](https://github.com/swannodette/mies) and further information can be found on its page.

## License

Copyright © 2015 Egg Davis

Distributed under the Eclipse Public License either version 1.0 or (at your option) any later version.
MathBox is copyright 2012 by Steven Wittens, and distributed under the MIT license.