package nl.bsoft.deeplink.database;

import io.hypersistence.utils.hibernate.type.json.JsonType;
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

    @Column(name = "md5hash")
    private String md5hash;

    @Type(JsonType.class)
    @Column(nullable = false, columnDefinition = "jsonb")
    private String content;

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
        return Objects.equals(identificatie, that.identificatie) && Objects.equals(version, that.version) && Objects.equals(created, that.created) && Objects.equals(md5hash, that.md5hash) && Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identificatie, version, created, md5hash, content);
    }

    @Override
    public String toString() {
        return "DeeplinkDto{" +
                "id=" + id +
                ", identificatie='" + identificatie + '\'' +
                ", version='" + version + '\'' +
                ", created=" + created +
                ", md5hash='" + md5hash + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
