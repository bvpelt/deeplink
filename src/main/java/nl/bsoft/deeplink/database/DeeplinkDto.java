package nl.bsoft.deeplink.database;

import com.fasterxml.jackson.databind.JsonNode;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.bsoft.deeplink.model.Deeplink;
import org.hibernate.annotations.Type;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
@Table(name ="deeplink", schema = "public", catalog = "deeplink")
public class DeeplinkDto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "identificatie")
    private String identificatie;

    @Column(name = "version")
    private String version;

    @Column(name = "created")
    private LocalDateTime created;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private JsonNode content;

    public DeeplinkDto(final Deeplink deeplink) {
        this.id = deeplink.getId();
        this.identificatie = deeplink.getIdentificatie();
        this.version = deeplink.getVersion();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeeplinkDto that = (DeeplinkDto) o;
        return Objects.equals(identificatie, that.identificatie) && Objects.equals(version, that.version) && Objects.equals(created, that.created) && Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identificatie, version, created, content);
    }

    @Override
    public String toString() {
        return "DeeplinkDto{" +
                "id=" + id +
                ", identificatie='" + identificatie + '\'' +
                ", version='" + version + '\'' +
                ", created=" + created +
                ", content='" + content + '\'' +
                '}';
    }
}
