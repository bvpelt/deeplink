package nl.bsoft.deeplink.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.bsoft.deeplink.database.DeeplinkDto;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Deeplink {
    private Long id;
    private String identificatie;
    private String version;
    private String content;

    public Deeplink(final DeeplinkDto deeplinkDto) {
        this.id = deeplinkDto.getId();
        this.identificatie = deeplinkDto.getIdentificatie();
        this.version = deeplinkDto.getVersion();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Deeplink deeplink = (Deeplink) o;
        return Objects.equals(identificatie, deeplink.identificatie) && Objects.equals(version, deeplink.version) && Objects.equals(content, deeplink.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identificatie, version, content);
    }

    @Override
    public String toString() {
        return "Deeplink{" +
                "id=" + id +
                ", identificatie='" + identificatie + '\'' +
                ", version='" + version + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
