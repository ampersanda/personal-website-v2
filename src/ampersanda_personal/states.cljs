(ns ^:figwheel-hooks ampersanda-personal.states
  (:require [reagent.core :as reagent
             :refer           [atom]]))

(defonce app-state (atom {:text "Halo semua!"}))
