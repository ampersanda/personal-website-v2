(ns ^:figwheel-hooks ampersanda-personal.events
  (:require [ampersanda-personal.states :as state]))

(defn >cursor-mode [mode]
  {:pre [(keyword? mode)]}

  (swap! state/app-state assoc :cursor-state mode))

(defn !!!cursor-mode []
  (swap! state/app-state assoc :cursor-state :normal))
