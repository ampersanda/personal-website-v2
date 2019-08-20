(ns ^:figwheel-hooks ampersanda-personal.pages.nav
  (:require
    [ampersanda-personal.events :as ev]
    [ampersanda-personal.routes :as routes]
    [ampersanda-personal.states :as state]
    [ampersanda-personal.widget.pointer-link :refer [+pointer-event]]
    [reagent.core :as reagent]))

(defn template []
  (reagent/create-class
   {:component-did-mount
    (fn [_]
      ;; TODO : animate alias classes
      (js/console.log "---"))

    :reagent-render
    (fn []
      [:div
       {:class "wrapper"}
       [:a
        (+pointer-event
         {:href (routes/url-for :home) :id "logo" :class "pointer-link editor use-global-font-var"})
        [:span {:class "alias alias__1"} "M"]
        [:span {:class "alias"} "o"]
        [:span {:class "alias"} "c"]
        [:span {:class "alias"} "h"]
        [:span {:class "alias alias__4"} "."]
        [:span {:class "alias"} " "]
        [:span {:class "alias alias__2"} "L"]
        [:span {:class "alias"} "u"]
        [:span {:class "alias"} "c"]
        [:span {:class "alias"} "k"]
        [:span {:class "alias"} "y"]
        [:span {:class "alias"} " "]
        [:span {:class "alias alias__3"} "P"]
        [:span {:class "alias"} "r"]
        [:span {:class "alias"} "a"]
        [:span {:class "alias"} "d"]
        [:span {:class "alias"} "a"]
        [:span {:class "alias"} "n"]
        [:span {:class "alias"} "a"]]

       [:ul
        [:li
         [:a
          (+pointer-event
           {:href  (routes/url-for :home)
            :class "pointer-link pointer-link__bottom-left"})
          "Home"]]
        [:li
         [:a
          (+pointer-event
           {:href  (routes/url-for :blog)
            :class "pointer-link pointer-link__bottom-left"})
          "Stories"]]
        [:li
         [:a
          (+pointer-event
           {:href  (routes/url-for :projects)
            :class "pointer-link pointer-link__bottom-left"})
          "Projects"]]
        [:li
         [:a
          (+pointer-event
           {:href  (routes/url-for :about)
            :class "pointer-link pointer-link__bottom-left"})
          "About"]]
        [:li
         [:a
          (+pointer-event
           {:href  (routes/url-for :contact)
            :class "pointer-link pointer-link__bottom-left"})
          "Contact"]]]])}))
