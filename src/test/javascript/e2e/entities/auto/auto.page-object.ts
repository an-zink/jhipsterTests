import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class AutoComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-auto div table .btn-danger'));
  title = element.all(by.css('jhi-auto div h2#page-heading span')).first();

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

export class AutoUpdatePage {
  pageTitle = element(by.id('jhi-auto-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  nameInput = element(by.id('field_name'));
  numberInput = element(by.id('field_number'));
  modellInput = element(by.id('field_modell'));

  async getPageTitle() {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setNameInput(name) {
    await this.nameInput.sendKeys(name);
  }

  async getNameInput() {
    return await this.nameInput.getAttribute('value');
  }

  async setNumberInput(number) {
    await this.numberInput.sendKeys(number);
  }

  async getNumberInput() {
    return await this.numberInput.getAttribute('value');
  }

  async setModellInput(modell) {
    await this.modellInput.sendKeys(modell);
  }

  async getModellInput() {
    return await this.modellInput.getAttribute('value');
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

export class AutoDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-auto-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-auto'));

  async getDialogTitle() {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
