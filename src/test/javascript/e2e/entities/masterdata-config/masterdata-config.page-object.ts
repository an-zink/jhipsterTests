import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class MasterdataConfigComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-masterdata-config div table .btn-danger'));
  title = element.all(by.css('jhi-masterdata-config div h2#page-heading span')).first();

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

export class MasterdataConfigUpdatePage {
  pageTitle = element(by.id('jhi-masterdata-config-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  nameInput = element(by.id('field_name'));
  pathInput = element(by.id('field_path'));
  clazzInput = element(by.id('field_clazz'));
  collectionNameInput = element(by.id('field_collectionName'));
  environmentInput = element(by.id('field_environment'));
  contentTypeInput = element(by.id('field_contentType'));
  portInput = element(by.id('field_port'));
  urlInput = element(by.id('field_url'));

  async getPageTitle() {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setNameInput(name) {
    await this.nameInput.sendKeys(name);
  }

  async getNameInput() {
    return await this.nameInput.getAttribute('value');
  }

  async setPathInput(path) {
    await this.pathInput.sendKeys(path);
  }

  async getPathInput() {
    return await this.pathInput.getAttribute('value');
  }

  async setClazzInput(clazz) {
    await this.clazzInput.sendKeys(clazz);
  }

  async getClazzInput() {
    return await this.clazzInput.getAttribute('value');
  }

  async setCollectionNameInput(collectionName) {
    await this.collectionNameInput.sendKeys(collectionName);
  }

  async getCollectionNameInput() {
    return await this.collectionNameInput.getAttribute('value');
  }

  async setEnvironmentInput(environment) {
    await this.environmentInput.sendKeys(environment);
  }

  async getEnvironmentInput() {
    return await this.environmentInput.getAttribute('value');
  }

  async setContentTypeInput(contentType) {
    await this.contentTypeInput.sendKeys(contentType);
  }

  async getContentTypeInput() {
    return await this.contentTypeInput.getAttribute('value');
  }

  async setPortInput(port) {
    await this.portInput.sendKeys(port);
  }

  async getPortInput() {
    return await this.portInput.getAttribute('value');
  }

  async setUrlInput(url) {
    await this.urlInput.sendKeys(url);
  }

  async getUrlInput() {
    return await this.urlInput.getAttribute('value');
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

export class MasterdataConfigDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-masterdataConfig-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-masterdataConfig'));

  async getDialogTitle() {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
