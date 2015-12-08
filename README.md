TODO: mention that base shader is included
TODO: document: must have local shaders file. Can get a default shaders file,
      snippets.glsl.html, from cljs-mathbox on github (or from cljs-mathbox-example?)
TODO: mention WebGL warnings



# cljs-mathbox

[cljs-mathbox](https://github.com/eggsyntax/cljs-mathbox) is a ClojureScript wrapper for the [MathBox 1](https://github.com/unconed/MathBox.js/tree/legacy) JavaScript mathematical visualization library.

## Overview

[MathBox](https://github.com/unconed/MathBox.js/tree/legacy) is an extremely powerful library for mathematical (and data) visualization in the browser, specializing in interactive, three-dimensional, animated visualizations, created by [Steven Wittens](http://acko.net/about/). It's built on the [Three.js](https://github.com/mrdoob/three.js/) library, which is built on [WebGL](https://en.wikipedia.org/wiki/WebGL). You can see a number of impressive MathBox visualizations at the author's website, [acko.net](http://acko.net/).

It should be noted that this library is based on MathBox version 1. MathBox version 2, which will be a very different, more powerful library, and is [nearing completion](http://acko.net/blog/mathbox2/) as of this writing (12/2015). Nevertheless, MathBox 1 is quite powerful in its own right.

For MathBox 1 documentation, see [its repo](https://github.com/unconed/MathBox.js/tree/legacy) (be aware that Mathbox 2 is [hosted elsewhere](https://gitgud.io/unconed/mathbox)). cljs-mathbox is a fairly thin wrapper over the original. It exposes the primitives (platonic, curve, bezier, surface, bezier-surface, vector, grid, axis, viewport, camera), and the major functions (animate, clone, remove, get-props, set-props).

You can see example usage of cljs-mathbox at the accompanying [cljs-mathbox-example](https://github.com/eggsyntax/cljs-mathbox-example). In short: include and require the library, create a mathbox instance, and call functions on it.

Note that you'll need at least one WebGL shader, and this repo [includes one](https://github.com/eggsyntax/cljs-mathbox/blob/master/shaders/snippets.glsl.html).

## Setup

Build your project once in dev mode with the following script and then open `index.html` in your browser.

    ./scripts/build

To auto build your project in dev mode:

    ./script/watch

To start an auto-building Node REPL (requires
[rlwrap](http://utopia.knoware.nl/~hlub/uck/rlwrap/), on OS X
installable via brew):

    ./scripts/repl

To get source map support in the Node REPL:

    lein npm install

To start a browser REPL:

1. Uncomment the following lines in src/cljs_mathbox/core.cljs:
```clojure
;; (defonce conn
;;   (repl/connect "http://localhost:9000/repl"))
```
2. Run `./scripts/brepl`
3. Browse to `http://localhost:9000` (you should see `Hello world!` in the web console)
4. (back to step 3) you should now see the REPL prompt: `cljs.user=>`
5. You may now evaluate ClojureScript statements in the browser context.

For more info using the browser as a REPL environment, see
[this](https://github.com/clojure/clojurescript/wiki/The-REPL-and-Evaluation-Environments#browser-as-evaluation-environment).

Clean project specific out:

    lein clean

Build a single release artifact with the following script and then open `index_release.html` in your browser.

    ./scripts/release

## License

Copyright © 2015 Egg Davis

Distributed under the Eclipse Public License either version 1.0 or (at your option) any later version.

MathBox is copyright 2012 by Steven Wittens, and distributed under the MIT license.