(ns com.pratishthan.demo.malli
    (:require [malli.core :as m]))

(def my-object-schema
  [:map
   [:a int?]
   [:b string?]
   [:c string?]
   [:d double?]
   ])

(defn validate-my-object [data]
  (let [result (m/explain my-object-schema data)]
      (println result)))

;; Example usage
(defn -main [& args]
      (let [data {:a "a123" :b "abc123" :c "2024-01-01" :d 2.0}]
           (validate-my-object data)
           ))

