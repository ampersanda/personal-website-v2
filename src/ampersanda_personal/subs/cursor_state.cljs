(ns ^:figwheel-hooks ampersanda-personal.subs.cursor-state
  (:require [ampersanda-personal.states :refer [app-state]]))

(defn expanded? []
  (= (:cursor-state @app-state) :expand))
