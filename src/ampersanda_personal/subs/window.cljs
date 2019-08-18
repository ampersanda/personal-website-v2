(ns ^:figwheel-hooks ampersanda-personal.subs.window-state
  (:require [ampersanda-personal.states :refer [app-state]]))

(defn window []
  (:window @app-state))
