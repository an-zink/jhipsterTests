
Creation of new StrapiCollectionTypes:

1. Check if needed class exist in package .domain. If yes continue with second point, if not look up
	in step "Create new CollectionType via jdl"
2. Open "Masterdata Config" via the dropdown-menue on the website 
3. Create new entry
	Convention for field "Clazz" example: "com.mycompany.mayapp.domain.ENTITY_KLASSE"
4. Remember for every CollectionType from every Strapi you need to create a new entry in Masterdata Config


Create new CollectionType via jdl
1. Define new entity with a jdl-File (for more information about jdl look up:https://www.jhipster.tech/jdl/intro)
2. Generate needed files with command: jhipster import-jdl "path to jdl-file".jdl
3. Add in generated ClassFile in package .domain "extends StrapiContentType"
4. (Optional) if you need the value of all your Strapi-CollectionTypes annotate needed attributes with 
	@JsonProperty("STRAPI_PROPERTY_NAME")
5. In class MasterdataConfig in package .schedule in Method load() add new If-statement 
	schema: look present 