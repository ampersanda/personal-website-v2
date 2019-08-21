(ns ^:figwheel-hooks ampersanda-personal.widget.cursor
  (:require [quil.core :as   q
             :include-macros true]
            [quil.middleware :as m]
            [reagent.core :refer [atom]]
            [ampersanda-personal.states :as state]
            [ampersanda-personal.subs.cursor-state :refer [expanded?]]))

(def speed 0.33)

(defn- setup []
  (q/frame-rate 60)
  (q/color-mode :hsb)
  {:x      0
   :y      0
   :mouse  {:x nil
            :y nil}
   :cursor {:dot      1
            :rotation 0
            :count    120
            :speed    speed
            :amt      0
            :min      20
            :max      48}})

(defn- ease-in-out-cubic [speed]
  (if (< speed 0.5)
    (* 4 speed speed speed)
    (inc (* (dec speed) (- (* 2 speed) 2) (- (* 2 speed) 2)))))

(defn- update-state [{:keys [x y cursor mouse] :as state}]
  (let [x-acc (+ x (* (- (:x mouse) x) speed))
        y-acc (+ y (* (- (:y mouse) y) speed))]
    (.setProperty js/document.documentElement.style "--global-slant" (* -10 (/ x-acc js/window.innerWidth)))
    (.setProperty js/document.documentElement.style "--global-wght" (+ 100 (* 800 (/ x-acc js/window.innerWidth))))

    {:x     x-acc
     :y     y-acc
     :mouse mouse

     :cursor
     (let [{:keys [amt speed count]} cursor
           count-min                 32
           count-max                 count
           count-step                8]
       (if (expanded?)
         (-> cursor
             (update :amt #(if (< amt 1) (+ % (ease-in-out-cubic speed)) %))
             (update :rotation #(+ % (ease-in-out-cubic 0.1)))
             (update :dot #(if (< % 4) (inc %) %))
             (update :count #(if (> % count-min) (- % count-step) %)))
         (-> cursor
             (update :amt #(if (pos? amt) (- % (ease-in-out-cubic speed)) %))
             (update :dot #(if (> % 1) (dec %) %))
             (update :count #(if (< % count-max) (+ % count-step) %)))))}))

(defn- draw-state [{:keys [cursor x y]}]
  (q/background 255)
  (q/stroke 150)
  (q/stroke-weight 1)

  (let [{:keys [min max amt rotation dot count]} cursor]
    (q/with-translation
     [x y]

     (q/with-rotation [rotation 1 1 0]
                      (let [radius (q/lerp min max amt)]
                        (doseq [i (range 0 q/TWO-PI (/ q/TWO-PI count))]
                          (q/fill 150)
                          (q/ellipse (* radius (q/cos i)) (* radius (q/sin i)) dot dot)))))))

(defn- mouse-moved [state event]
  (assoc state :mouse
         {:x (:x event)
          :y (:y event)}))

(defn execute! [width height]
  {:pre [(and (number? width)
              (number? height))]}
  (q/defsketch cursor
    :host "cursor"
    :size [width height]
    :setup setup
    :update update-state
    :mouse-moved mouse-moved
    :draw draw-state
    :middleware [m/fun-mode]))
