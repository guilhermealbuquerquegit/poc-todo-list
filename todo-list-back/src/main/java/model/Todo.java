package model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
public class Todo {

    @Id
    @JsonProperty("id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("nome")
    @Column(name="nome")
    @NotNull(message = "{todo.name.notnull}")
    private String nome;


    @JsonProperty("done")
    @Column(name="done")
    @NotNull(message = "{todo.done.notnull}")
    private Boolean done;

    public Long getId() {
        return id;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Todo todo = (Todo) o;
        return Objects.equals(id, todo.id) &&
                Objects.equals(nome, todo.nome) &&
                Objects.equals(done, todo.done);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, done);
    }

    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", done=" + done +
                '}';
    }
}