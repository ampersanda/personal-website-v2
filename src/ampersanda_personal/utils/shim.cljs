(ns ^:figwheel-hooks ampersanda-personal.utils.shim
  (:require [goog.object :as g]
            [reagent.core :as reagent]
            [ampersanda-personal.routes :as routes]))

(def vendors [:ms :moz :webkit :o])

(defn abs [n] (max n (- n)))

(defonce scroll-attrs
  (atom
   {:wrapper    nil
    :div        nil
    :scroll-top 0
    :tweened    0
    :win-height 70}))

(defn- resize-fallback []
  (if (nil? (.-createEvent js/document))
    (js/window.dispatchEvent (new Event. "resize"))
    (let [resize-event (js/window.document.createEvent "UIEvents")]
      (.initUIEvent resize-event "resize" true false js/window 0)
      (js/window.dispatchEvent resize-event))))

(defn- set-wrapper-attrs [wrapper]
  (set! (.. wrapper -style -position) "fixed")
  (set! (.. wrapper -style -width) "100%")
  (set! (.. wrapper -style -top) 0)
  (set! (.. wrapper -style -left) 0)
  (set! (.. wrapper -style -transform) "translateY(0)"))

(defn- update-div-height []
  (let [{:keys [div wrapper]} @scroll-attrs]
    (set! (.. div -style -height) (str (.-clientHeight wrapper) "px"))))

(defn- make-dummy-div [wrapper]
  (let [div (js/document.createElement "div")]
    (. div setAttribute "id" "scrollbar")
    (swap! scroll-attrs assoc :div div)
    (update-div-height)
    (js/document.body.appendChild div)))

(defn- update-scroll []
  (js/window.requestAnimationFrame update-scroll)

  (let [{:keys [scroll-top tweened win-height wrapper div]} @scroll-attrs]

    (when (> (abs (- scroll-top tweened)) 0)
      (swap! scroll-attrs update :tweened #(+ % (* 0.4 (- scroll-top tweened))))

      (when-let [cursor (js/document.getElementById "cursor")]
        (set! (.. cursor -style -transform)
          (str "translateY(" tweened "px)")))

      (set! (.. wrapper -style -transform)
        (str "translateY(" (* -1 tweened) "px)"))
      (update-div-height))))

(defn- on-scroll []
  (swap! scroll-attrs assoc :scroll-top
         (js/Math.max 0
                      (or (.. js/document -documentElement -scrollTop)
                          (.-pageYOffset js/window) 0))))

(defn on-resize []
  (update-scroll)
  (update-div-height))

(defn reset-scroll []
  (reset! scroll-attrs
          {:wrapper    nil
           :div        nil
           :scroll-top 0
           :tweened    0
           :win-height 0})

  (when-let [cursor (js/document.getElementById "cursor")]
    (set! (.. cursor -style -transform)
      (str "translateY(0)")))

  (when-let [scrollbar (js/document.getElementById "scrollbar")]
    (. (.-parentNode scrollbar) removeChild scrollbar))

  (js/window.removeEventListener "resize" on-resize)
  (js/window.removeEventListener "scroll" on-scroll))

(defn shim-scroll []
  (when-let [wrapper (js/document.getElementById "wrap")]
    (swap! scroll-attrs assoc :wrapper wrapper)
    (set-wrapper-attrs wrapper)
    (make-dummy-div wrapper)

    (js/window.addEventListener "scroll" on-scroll)
    (update-scroll)

    (js/window.addEventListener "resize" on-resize)))

(defn render-with-shim
  ([content]
   (reagent/create-class
    {:component-did-mount
     (fn [_]
       ;; shim page scroll
       (shim-scroll))

     :component-will-unmount
     (fn []
       (reset-scroll))

     :reagent-render
     (fn [_]
       content)}))

  ([content component-did-mount component-will-unmount]
   {:pre [(and (fn? component-did-mount)
               (fn? component-will-unmount))]}
   (reagent/create-class
    {:component-did-mount
     (fn [_]
       (shim-scroll)
       (component-did-mount _))

     :component-will-unmount
     (fn []
       (reset-scroll)
       (component-will-unmount))

     :reagent-render
     (fn [_]
       content)})))

;(defn raf-fallback []
;  (let [i         (atom 0)
;        interval  (atom nil)
;        last-time (atom 0)]
;
;    (reset! interval
;            #(js/setInterval
;              (fn []
;                (resize!)
;                (when (= @i 5) (js/clearInterval interval))
;                (reset! i (inc @i)))))
;
;    (when-not (nil? interval) (@interval))
;
;    (if-not (nil? (.-requestAnimationFrame js/window))
;      (doseq [vendor vendors]
;        (let [raf (g/get js/window (str (name vendor) "RequestAnimationFrame"))]
;          (when-not (nil? raf)
;            (js/console.log (. js/window -requestAnimationFrame))
;            (js/console.log raf)))
;        ))))
