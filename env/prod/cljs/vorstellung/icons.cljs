(ns vorstellung.icons
  (:require
   [vorstellung.charts.core :as core]))

;;ignore println statements in prod
(set! *print-fn* (fn [& _]))

(core/init!)
