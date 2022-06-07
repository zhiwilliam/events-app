provider "kubernetes" {
  config_path = "~/.kube/config"
}

resource "kubernetes_namespace_v1" "events_app_ns" {
  metadata {
    annotations = {
      name = "evens-app-annotation"
    }

    labels = {
      mylabel = "events-app"
    }

    name = "terraform-events-app-namespace"
  }
}