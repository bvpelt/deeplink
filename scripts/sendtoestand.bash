curl -X 'POST' \
  'http://localhost:8082/toestanden' \
  -H 'accept: application/json' \
  -H 'Content-Type: application/json' \
  -d '{
  "version": "0.0.1",
  "toestand": "{
  \"_embedded\": {
    \"plan\": {
      \"_links\": {
        \"self\": {
          \"href\": \"https://ruimte.omgevingswet.overheid.nl/ruimtelijke-plannen/api/opvragen/v4/plannen/NL.IMRO.0392.BP3120023-va01\"
        }
      },
      \"relatiesVanuitExternePlannen\": {
        \"tenGevolgeVan\": [],
        \"gedeeltelijkeHerzieningVan\": [],
        \"uitTeWerkenIn\": [],
        \"vervangt\": [],
        \"uitgewerktIn\": [],
        \"gebruiktInformatieUit\": [],
        \"muteert\": []
      },
      \"regelStatus\": \"geldend\",
      \"ondergronden\": [
        {
          \"datum\": \"2023-11-20\",
          \"type\": \"basisregistratie grootschalige topografie (BGT)\"
        },
        {
          \"datum\": \"2023-11-20\",
          \"type\": \"basisregistratie kadaster (BRK)\"
        }
      ],
      \"normadressant\": [],
      \"heeftOnderdelen\": [
        {
          \"type\": \"regels\",
          \"heeftObjectgerichteTeksten\": [
            {
              \"titel\": \"Regels\",
              \"href\": \"https://ruimte.omgevingswet.overheid.nl/ruimtelijke-plannen/api/opvragen/v4/plannen/NL.IMRO.0392.BP3120023-va01/teksten/NL.IMRO.PT.s112\",
              \"type\": \"regels\"
            }
          ],
          \"externeReferenties\": [
            \"https://www.ruimtelijkeplannen.nl/documents/NL.IMRO.0392.BP3120023-va01/pt_NL.IMRO.0392.BP3120023-va01.xml#NL.IMRO.PT.s112\"
          ]
        },
        {
          \"type\": \"bijlage bij toelichting\",
          \"heeftObjectgerichteTeksten\": [
            {
              \"titel\": \"BIJLAGEN BIJ DE TOELICHTING\",
              \"href\": \"https://ruimte.omgevingswet.overheid.nl/ruimtelijke-plannen/api/opvragen/v4/plannen/NL.IMRO.0392.BP3120023-va01/teksten/NL.IMRO.PT.s106\",
              \"type\": \"bijlagen bij toelichting\"
            }
          ],
          \"externeReferenties\": [
            \"https://www.ruimtelijkeplannen.nl/documents/NL.IMRO.0392.BP3120023-va01/pt_NL.IMRO.0392.BP3120023-va01.xml#NL.IMRO.PT.s106\"
          ]
        },
        {
          \"type\": \"toelichting\",
          \"heeftObjectgerichteTeksten\": [
            {
              \"titel\": \"Toelichting\",
              \"href\": \"https://ruimte.omgevingswet.overheid.nl/ruimtelijke-plannen/api/opvragen/v4/plannen/NL.IMRO.0392.BP3120023-va01/teksten/NL.IMRO.PT.s1\",
              \"type\": \"toelichting\"
            }
          ],
          \"externeReferenties\": [
            \"https://www.ruimtelijkeplannen.nl/documents/NL.IMRO.0392.BP3120023-va01/pt_NL.IMRO.0392.BP3120023-va01.xml#NL.IMRO.PT.s1\"
          ]
        }
      ],
      \"type\": \"bestemmingsplan\",
      \"verwijzingNaarGml\": \"https://www.ruimtelijkeplannen.nl/documents/NL.IMRO.0392.BP3120023-va01/NL.IMRO.0392.BP3120023-va01.gml\",
      \"planstatusInfo\": {
        \"datum\": \"2025-03-28\",
        \"planstatus\": \"vastgesteld\"
      },
      \"beleidsmatigVerantwoordelijkeOverheid\": {
        \"code\": \"0392\",
        \"type\": \"gemeentelijke overheid\",
        \"naam\": \"gemeente Haarlem\"
      },
      \"verwijzingNorm\": [
        \"IMRO2012\",
        \"PRBP2012\",
        \"PRABPK2012\",
        \"IMROPT2012\"
      ],
      \"relatiesMetExternePlannen\": {
        \"tenGevolgeVan\": [],
        \"gedeeltelijkeHerzieningVan\": [],
        \"uitTeWerkenIn\": [],
        \"vervangt\": [],
        \"uitgewerktIn\": [],
        \"gebruiktInformatieUit\": [],
        \"muteert\": []
      },
      \"id\": \"NL.IMRO.0392.BP3120023-va01\",
      \"besluitnummer\": \"2023/1995785\",
      \"verwijzingNaarVaststellingsbesluit\": \"https://www.ruimtelijkeplannen.nl/documents/NL.IMRO.0392.BP3120023-va01/vb_NL.IMRO.0392.BP3120023-va01.pdf\",
      \"locatienamen\": [
        \"Liewegje e.o.\"
      ],
      \"dossier\": {
        \"id\": \"NL.IMRO.0392.BP3120023\",
        \"status\": \"geheel in werking\"
      },
      \"naam\": \"Domus Plus - Fuikvaartweg\",
      \"regelBinding\": \"burgerbindend\",
      \"isTamPlan\": false,
      \"beroepEnBezwaar\": \"ja\",
      \"isHistorisch\": false,
      \"verwijderdOp\": null,
      \"isParapluplan\": false,
      \"illustraties\": [],
      \"eindeRechtsgeldigheid\": null,
      \"publicerendBevoegdGezag\": {
        \"code\": \"0392\",
        \"type\": \"gemeentelijke overheid\",
        \"naam\": \"gemeente Haarlem\"
      }
    }
  }
}"
}'