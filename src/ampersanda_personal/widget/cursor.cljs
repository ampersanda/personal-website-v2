(ns ^:figwheel-hooks ampersanda-personal.widget.cursor
  (:require [quil.core :as   q
             :include-macros true]
            [quil.middleware :as m]
            [ampersanda-personal.states :as state]
            [ampersanda-personal.subs.cursor-state :refer [expanded?]]))

(def speed 0.33)

(defn- setup []
  (q/frame-rate 30)
  (q/color-mode :hsb)
  {:x      0
   :y      0
   :mouse  {:x nil
            :y nil}
   :cursor {:speed speed
            :amt   0
            :min   40
            :max   88}})

(defn- ease-in-out-cubic [speed]
  (if (< speed 0.5)
    (* 4 speed speed speed)
    (inc (* (dec speed) (- (* 2 speed) 2) (- (* 2 speed) 2)))))

(defn- update-state [{:keys [x y cursor mouse]}]
  (.setProperty js/document.documentElement.style "--global-slant" (* -10 (/ (+ x (* (- (:x mouse) x) speed)) js/window.innerWidth)))

  {:x          (+ x (* (- (:x mouse) x) speed))

   :y          (+ y (* (- (:y mouse) y) speed))

   :mouse      mouse

   :cursor
   (let [{:keys [amt min max speed]} cursor]
     (cond
       (and (expanded?) (< amt 1))                    (update cursor :amt #(+ % (ease-in-out-cubic speed)))
       (and (not (expanded?)) (> amt 0))              (update cursor :amt #(- % (ease-in-out-cubic speed)))
       :else                                          cursor))})

(defn- draw-state [{:keys [cursor x y]}]
  (q/background 255)
  (q/stroke 150)

  (let [{:keys [min max amt]} cursor]
    (if (expanded?)
      (q/stroke-weight (q/lerp 1 2 amt))
      (q/stroke-weight 1))

    (q/ellipse x y (q/lerp min max amt) (q/lerp min max amt))))

(defn- mouse-moved [state event]
  (assoc state :mouse
         {:x (:x event)
          :y (:y event)}))

(defn execute! [width height]
  (q/defsketch cursor
    :host "cursor"
    :size [width height]
    :setup setup
    :update update-state
    :mouse-moved mouse-moved
    :draw draw-state
    :middleware [m/fun-mode]))
