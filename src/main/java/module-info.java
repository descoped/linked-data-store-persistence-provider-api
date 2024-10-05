module io.descoped.lds.persistence.api {
    requires io.reactivex.rxjava2;
    requires org.reactivestreams;
    requires com.github.akarnokd.rxjava2jdk9interop;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;

    exports io.descoped.lds.api.persistence;
    exports io.descoped.lds.api.persistence.flattened;
    exports io.descoped.lds.api.persistence.streaming;
    exports io.descoped.lds.api.persistence.json;
    exports io.descoped.lds.api.persistence.reactivex;
    exports io.descoped.lds.api.specification;
    exports io.descoped.lds.api.json;
}
