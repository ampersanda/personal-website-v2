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
   {:cursor-state :normal
    :window       {:width  js/window.innerWidth
                   :height js/window.innerHeight}}))
