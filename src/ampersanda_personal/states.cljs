(ns ^:figwheel-hooks ampersanda-personal.states
  (:require [reagent.core :as reagent
             :refer           [atom]]))

;; CURSOR STATE
;; :normal
;; :expand
;; :arrow-right
;; :arrow-left

(defonce app-state
  (atom
   {:text         "Halo semua!"
    :cursor-state :normal}))
