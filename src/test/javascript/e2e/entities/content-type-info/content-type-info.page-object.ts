import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class ContentTypeInfoComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-content-type-info div table .btn-danger'));
  title = element.all(by.css('jhi-content-type-info div h2#page-heading span')).first();

  async clickOnCreateButton(timeout?: number) {
    await this.createButton.click();
  }

  async clickOnLastDeleteButton(timeout?: number) {
    await this.deleteButtons.last().click();
  }

  async countDeleteButtons() {
    return this.deleteButtons.count();
  }

  async getTitle() {
    return this.title.getAttribute('jhiTranslate');
  }
}

export class ContentTypeInfoUpdatePage {
  pageTitle = element(by.id('jhi-content-type-info-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  contentTypeNameInput = element(by.id('field_contentTypeName'));
  enviromentInput = element(by.id('field_enviroment'));
  lastModifiedByStrapiUserInput = element(by.id('field_lastModifiedByStrapiUser'));
  lastModifiedDateInput = element(by.id('field_lastModifiedDate'));
  numberOfEntriesInput = element(by.id('field_numberOfEntries'));
  numberOfParametersInput = element(by.id('field_numberOfParameters'));

  async getPageTitle() {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setContentTypeNameInput(contentTypeName) {
    await this.contentTypeNameInput.sendKeys(contentTypeName);
  }

  async getContentTypeNameInput() {
    return await this.contentTypeNameInput.getAttribute('value');
  }

  async setEnviromentInput(enviroment) {
    await this.enviromentInput.sendKeys(enviroment);
  }

  async getEnviromentInput() {
    return await this.enviromentInput.getAttribute('value');
  }

  async setLastModifiedByStrapiUserInput(lastModifiedByStrapiUser) {
    await this.lastModifiedByStrapiUserInput.sendKeys(lastModifiedByStrapiUser);
  }

  async getLastModifiedByStrapiUserInput() {
    return await this.lastModifiedByStrapiUserInput.getAttribute('value');
  }

  async setLastModifiedDateInput(lastModifiedDate) {
    await this.lastModifiedDateInput.sendKeys(lastModifiedDate);
  }

  async getLastModifiedDateInput() {
    return await this.lastModifiedDateInput.getAttribute('value');
  }

  async setNumberOfEntriesInput(numberOfEntries) {
    await this.numberOfEntriesInput.sendKeys(numberOfEntries);
  }

  async getNumberOfEntriesInput() {
    return await this.numberOfEntriesInput.getAttribute('value');
  }

  async setNumberOfParametersInput(numberOfParameters) {
    await this.numberOfParametersInput.sendKeys(numberOfParameters);
  }

  async getNumberOfParametersInput() {
    return await this.numberOfParametersInput.getAttribute('value');
  }

  async save(timeout?: number) {
    await this.saveButton.click();
  }

  async cancel(timeout?: number) {
    await this.cancelButton.click();
  }

  getSaveButton(): ElementFinder {
    return this.saveButton;
  }
}

export class ContentTypeInfoDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-contentTypeInfo-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-contentTypeInfo'));

  async getDialogTitle() {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
