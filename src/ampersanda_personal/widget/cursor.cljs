(ns ^:figwheel-hooks ampersanda-personal.widget.cursor
  (:require [quil.core :as   q
             :include-macros true]
            [quil.middleware :as m]
            [reagent.core :refer [atom]]
            [ampersanda-personal.states :as state]
            [ampersanda-personal.subs.window-state :refer [window]]
            [ampersanda-personal.subs.cursor-state :refer [expanded?]]))

(def speed 0.33)

(defn- setup []
  (q/frame-rate 30)
  (q/color-mode :hsb)
  {:x      0
   :y      0
   :mouse  {:x nil
            :y nil}
   :cursor {:rotation 0
            :speed    speed
            :amt      0
            :min      40
            :max      64}})

(defn- ease-in-out-cubic [speed]
  (if (< speed 0.5)
    (* 4 speed speed speed)
    (inc (* (dec speed) (- (* 2 speed) 2) (- (* 2 speed) 2)))))

(defn- update-state [{:keys [x y cursor mouse] :as state}]
  (let [x-acc (+ x (* (- (:x mouse) x) speed))
        y-acc (+ y (* (- (:y mouse) y) speed))]
    (.setProperty js/document.documentElement.style "--global-slant" (* -10 (/ x-acc js/window.innerWidth)))


    {:x     x-acc
     :y     y-acc
     :mouse mouse

     :cursor
     (let [{:keys [amt speed]} cursor]

       (cond
         (and (expanded?) (< amt 1))                    (update cursor :amt #(+ % (ease-in-out-cubic speed)))
         (and (not (expanded?)) (> amt 0))              (update cursor :amt #(- % (ease-in-out-cubic speed)))
         (expanded?)                                    (update cursor :rotation #(+ % (ease-in-out-cubic speed)))
         :else                                          cursor))}))

(defn- draw-state [{:keys [cursor x y]}]
  (q/background 255)
  (q/stroke 150)

  (let [{:keys [min max amt rotation]} cursor]
    (if (expanded?)
      (q/stroke-weight (q/lerp 1 2 amt))
      (q/stroke-weight 1))

    (q/with-translation
     [x y]
     (q/with-rotation [rotation 1 1 0]
                      (let [count  (if (expanded?) 40 120)
                            radius (q/lerp min max amt)]
                        (doseq [i (range 0 q/TWO-PI (/ q/TWO-PI count))]
                          (q/ellipse (* radius (q/cos i)) (* radius (q/sin i)) 1 1)))))))

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
