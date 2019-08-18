(ns ^:figwheel-hooks ampersanda-personal.widget.fractal
  (:require [quil.core :as   q
             :include-macros true]
            [quil.middleware :as m]
            [ampersanda-personal.states :as state]
            [ampersanda-personal.subs.cursor-state :refer [expanded?]]))


(def branch-length 120)

(defn- branch [length angle]
  (q/line 0 0 0 (- length))
  (q/translate 0 (- length))

  (when (> length 4)
    (q/with-rotation
     [angle]
     (branch (* length 0.67) angle))
    (q/with-rotation
     [(- angle)]
     (branch (* length 0.67) angle))))

(defn- setup []
  (q/frame-rate 2)
  (q/color-mode :rgb)
  {:angle (/ q/PI 4)})

(defn- update-state [{:keys [angle] :as state}]
  {:angle (- angle (* q/PI 0.01))})

(defn- draw [{:keys [angle] :as state}]
  (q/background 78 177 171)

  (q/stroke 255)

  (q/translate (/ (q/width) 2) (q/height))
  (branch branch-length angle))

(defn execute! [host width height]
  {:pre [(and (string? host)
              (number? width)
              (number? height))]}
  (q/defsketch fractal
    :host host
    :size [width height]
    :setup setup
    :draw draw
    :update update-state
    :middleware [m/fun-mode]))
