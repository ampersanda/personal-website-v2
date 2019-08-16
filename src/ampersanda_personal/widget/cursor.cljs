(ns ^:figwheel-hooks ampersanda-personal.widget.cursor
  (:require [quil.core :as   q
             :include-macros true]
            [quil.middleware :as m]
            [ampersanda-personal.states :as state]
            [ampersanda-personal.subs.cursor-state :refer [expanded?]]
            [reagent.core :refer [atom]]))

(defn- setup []
  (q/frame-rate 30)
  (q/color-mode :hsb)
  {:x      0
   :y      0
   :cursor {:speed 0.08
            :amt   0
            :min   40
            :max   100}})

(defn- update-state [{:keys [x y cursor]}]
  {:x x

   :y y

   :cursor
   (let [{:keys [amt min max speed]} cursor
         ease-out-quad (* speed (- 2 speed))]
     (cond
       (and (expanded?) (< amt 1))    (update cursor :amt #(+ % ease-out-quad))
       (and (not (expanded?)) (> amt 0))              (update cursor :amt #(- % ease-out-quad))
       :else                          cursor))})

(defn- draw-state [{:keys [cursor x y]}]
  (q/background 255)
  (q/stroke 150)

  (let [{:keys [min max amt speed]} cursor]
    (js/console.log amt)
    (q/ellipse x y (q/lerp min max amt) (q/lerp min max amt))))

;; FIXME : need easing
(defn- mouse-moved [{:keys [cursor]} {:keys [x y]}]
  {:x      x
   :y      y
   :cursor cursor})

(defn execute! [width height]
  (q/defsketch cursor
    :host "cursor"
    :size [width height]
    :setup setup
    :update update-state
    :mouse-moved mouse-moved
    :draw draw-state
    :middleware [m/fun-mode]))
