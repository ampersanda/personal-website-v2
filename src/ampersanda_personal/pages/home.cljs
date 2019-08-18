(ns ^:figwheel-hooks ampersanda-personal.pages.home
  (:require
    [ampersanda-personal.events :as ev]
    [ampersanda-personal.routes :as routes]
    [ampersanda-personal.states :as state]
    [ampersanda-personal.widget.wave :as wave]
    [ampersanda-personal.utils.shim :refer [render-with-shim]]
    [ampersanda-personal.widget.pointer-link :refer [+pointer-event]]
    [reagent.core :as reagent]))


(defn template []
  (let [refs    (reagent/atom {})
        size    (reagent/atom {})
        resizer #(let [{:keys [wave]}     @refs
                       parent-node-style  (js/getComputedStyle (.-parentNode wave))
                       width              (js/parseFloat (.-width parent-node-style))
                       height             (js/parseFloat (.-height parent-node-style))]
                  (wave/execute! (.-id wave) width height))]
    (render-with-shim
     [:main
      {:class "content editor"}
      [:h1 {:class "jumbo use-global-slant"} "Hello!"]
      [:h1
       "My name is "
       [:span {:style {:color "var(--blue)"}} "Mochamad Lucky Pradana"]
       ". I am Front End Developer and (ocassionally) Flutter Developer who live in "
       [:a
        (+pointer-event {:href "https://goo.gl/maps/sJumGDqZ8KVz16nG7"})
        "Surabaya, Indonesia"]
       "."]

      [:div
       {:class "grid-menu normal-cursor"}
       [:a
        {:class "grid-menu--item"
         :style {:background "var(--blue)" :href (routes/url-for :blog)}}
        [:h1 {:class "grid-menu--title"} "Stories"]]
       [:a
        {:class "grid-menu--item"
         :href  (routes/url-for :projects)}
        [:div
         {:id "wave" :class "grid-menu--item-canvas" :ref #(swap! refs assoc :wave %)}]
        [:h1 {:class "grid-menu--title"} "Projects"]]
       [:a
        {:class "grid-menu--item grid-menu--item__small grid-menu--item__square"
         :style {:background "#ed4d8b"}
         :href  (routes/url-for :about)}
        [:h1 {:class "grid-menu--title"} "About"]]
       [:a
        {:class "grid-menu--item grid-menu--item__small grid-menu--item__square"
         :style {:background "#ebedf9"}
         :href  (routes/url-for :contact)}
        [:h1 {:class "grid-menu--title"} "Contact"]]]]

     (fn [_]
       (js/window.addEventListener "resize" resizer)
       (resizer))

     (fn []
       (js/window.removeEventListener "resize" resizer)))))
