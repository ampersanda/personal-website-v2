(ns ^:figwheel-hooks ampersanda-personal.pages.nav
  (:require
    [ampersanda-personal.events :as ev]
    [ampersanda-personal.routes :as routes]
    [ampersanda-personal.states :as state]
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
       [:nav
        [:a
         {:href (routes/url-for :home) :id "logo" :class "no-cursor pointer-link"}
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
         [:span {:class "alias"} "a"]]]])}))
