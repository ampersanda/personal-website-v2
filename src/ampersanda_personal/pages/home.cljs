(ns ^:figwheel-hooks ampersanda-personal.pages.home
  (:require
    [ampersanda-personal.events :as ev]
    [ampersanda-personal.routes :as routes]
    [ampersanda-personal.states :as state]
    [ampersanda-personal.widget.pointer-link :refer [+pointer-event]]
    [reagent.core :as reagent]))

(defn template []
 [:main
  {:class "content editor"}
  [:h1 {:class "jumbo use-global-slant"} "Hello!"]
  [:h1 "My name is " [:span {:style {:color "var(--blue)"}} "Mochamad Lucky Pradana"] ". I am Front End Developer and (ocassionally) Flutter Developer who live in " [:a {:href "https://goo.gl/maps/sJumGDqZ8KVz16nG7"} "Surabaya, Indonesia"] "."]

  [:div {:class "grid-menu normal-cursor"}
   [:a {:class "grid-menu--item use-global-slant" :style {:background "var(--blue)" :href "#"}}
    [:h1 {:class "grid-menu--title"} "Stories"]]
   [:a {:class "grid-menu--item use-global-slant" :style {:background :coral} :href "#"}
    [:h1 {:class "grid-menu--title"} "Projects"]]
   [:a {:class "grid-menu--item grid-menu--item__small grid-menu--item__square use-global-slant" :style {:background "#ed4d8b"} :href "#"}
    [:h1 {:class "grid-menu--title"} "About"]]
   [:a {:class "grid-menu--item grid-menu--item__small grid-menu--item__square use-global-slant" :style {:background "#ebedf9"} :href "#"}
    [:h1 {:class "grid-menu--title"} "Stories"]]]])
